package com.example.storageservice.repository.entity;

import com.example.storageservice.rest.entity.StorageType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "audio_file_storage")
public class Storage {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "storage_type", unique = true)
	private StorageType storageType;

	@Column(name = "bucket_name")
	private String bucket;

	@Column(name = "resource_path")
	private String path;

	public Storage() {
	}

}
