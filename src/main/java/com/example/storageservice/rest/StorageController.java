package com.example.storageservice.rest;

import com.example.storageservice.repository.StorageRepository;
import com.example.storageservice.repository.entity.Storage;
import com.example.storageservice.rest.entity.StorageTypeRequest;
import com.example.storageservice.rest.entity.StorageTypeResponse;
import com.example.storageservice.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/storages")
@RestController
public class StorageController {

	private final StorageRepository storageRepository;

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Long> createStorageType(@RequestBody StorageTypeRequest storageTypeRequest) {

		Storage storage = Mapper.mapToStorage(storageTypeRequest);

		return new HttpEntity<>(storageRepository.save(storage).getId());
	}

	@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'USER')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<List<StorageTypeResponse>> getStorageTypes() {
		log.info("Call to retrieve data about storage types");

		return new HttpEntity<>(storageRepository.findAll().stream()
				.map(Mapper::mapToStorageTypeResponse)
				.toList());
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping
	public HttpEntity<List<Long>> deleteStorageTypes(@RequestParam("id") List<Long> ids) {
		return new HttpEntity<>(storageRepository.deleteAllByIds(ids));
	}

}
