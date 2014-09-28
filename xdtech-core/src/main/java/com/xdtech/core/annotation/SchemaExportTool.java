package com.xdtech.core.annotation;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.MappingException;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
  
public class SchemaExportTool extends Configuration {  
  
    private static final long serialVersionUID = 1L;  
  
    private static final String RESOURCE_PATTERN = "/**/*.class";  
  
    private static final String PACKAGE_INFO_SUFFIX = ".package-info";  
  
    private static final TypeFilter[] ENTITY_TYPE_FILTERS = new TypeFilter[] {  
            new AnnotationTypeFilter(Entity.class, false),  
            new AnnotationTypeFilter(Embeddable.class, false),  
            new AnnotationTypeFilter(MappedSuperclass.class, false) };  
    private final ResourcePatternResolver resourcePatternResolver;  
  
    public SchemaExportTool() {  
        this.resourcePatternResolver = ResourcePatternUtils  
                .getResourcePatternResolver(new PathMatchingResourcePatternResolver());  
    }  
  
  
    private SchemaExportTool scanPackage(String... packagesToScan) {  
        try {  
            for (String pkg : packagesToScan) {  
                String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX  
                        + ClassUtils.convertClassNameToResourcePath(pkg)  
                        + RESOURCE_PATTERN;  
                Resource[] resources = this.resourcePatternResolver  
                        .getResources(pattern);  
                MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(  
                        this.resourcePatternResolver);  
                for (Resource resource : resources) {  
                    if (resource.isReadable()) {  
                        MetadataReader reader = readerFactory  
                                .getMetadataReader(resource);  
                        String className = reader.getClassMetadata()  
                                .getClassName();  
                        if (matchesEntityTypeFilter(reader, readerFactory)) {  
                            addAnnotatedClass(this.resourcePatternResolver  
                                    .getClassLoader().loadClass(className));  
                        } else if (className.endsWith(PACKAGE_INFO_SUFFIX)) {  
                            addPackage(className.substring(  
                                    0,  
                                    className.length()  
                                            - PACKAGE_INFO_SUFFIX.length()));  
                        }  
                    }  
                }  
            }  
            return this;  
        } catch (IOException ex) {  
            throw new MappingException(  
                    "Failed to scan classpath for unlisted classes", ex);  
        } catch (ClassNotFoundException ex) {  
            throw new MappingException(  
                    "Failed to load annotated classes from classpath", ex);  
        }  
    }  
  
    /** 
     * Check whether any of the configured entity type filters matches the 
     * current class descriptor contained in the metadata reader. 
     */  
    private boolean matchesEntityTypeFilter(MetadataReader reader,  
            MetadataReaderFactory readerFactory) throws IOException {  
        for (TypeFilter filter : ENTITY_TYPE_FILTERS) {  
            if (filter.match(reader, readerFactory)) {  
                return true;  
            }  
        }  
        return false;  
    }  
  
}  
