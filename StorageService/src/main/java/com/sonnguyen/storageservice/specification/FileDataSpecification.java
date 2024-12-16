package com.sonnguyen.storageservice.specification;


import com.sonnguyen.common.specification.AbstractSpecification;
import com.sonnguyen.common.specification.DynamicSearch;
import com.sonnguyen.storageservice.model.FileData;

public class FileDataSpecification extends AbstractSpecification<FileData> {
    public FileDataSpecification(DynamicSearch criteria) {
        super(criteria);
    }
}
