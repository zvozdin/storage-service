package com.example.storageservice.util;

import com.example.storageservice.repository.entity.Storage;
import com.example.storageservice.rest.entity.StorageTypeRequest;
import com.example.storageservice.rest.entity.StorageTypeResponse;

public class Mapper {

	public static Storage mapToStorage(StorageTypeRequest request) {
		Storage storage = new Storage();
		storage.setStorageType(request.storageType());
		storage.setBucket(request.bucket());
		storage.setPath(request.path());

		return storage;
	}

	public static StorageTypeResponse mapToStorageTypeResponse(Storage storage) {
		return new StorageTypeResponse(
				storage.getId(),
				storage.getStorageType(),
				storage.getBucket(),
				storage.getPath()
		);
	}

}
