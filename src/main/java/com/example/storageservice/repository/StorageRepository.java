package com.example.storageservice.repository;

import com.example.storageservice.repository.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {

	default List<Long> deleteAllByIds(List<Long> ids) {
		List<Long> existedIds = ids.stream().filter(this::existsById).toList();
		deleteAllById(existedIds);
		return existedIds;
	}

}
