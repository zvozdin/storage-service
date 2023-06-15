package com.example.storageservice.rest.entity;

public record StorageTypeRequest(StorageType storageType, String bucket, String path) {
}
