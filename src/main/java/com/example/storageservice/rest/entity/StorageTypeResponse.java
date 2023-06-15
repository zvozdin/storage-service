package com.example.storageservice.rest.entity;

public record StorageTypeResponse(Long id, StorageType storageType, String bucket, String path) {
}
