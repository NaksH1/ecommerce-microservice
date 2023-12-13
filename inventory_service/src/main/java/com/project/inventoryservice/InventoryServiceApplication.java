package com.project.inventoryservice;

import com.project.inventoryservice.entity.Inventory;
import com.project.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {

			Inventory inventory = Inventory.builder()
					.skuCode("Iphone 12 pro")
					.quantity(1)
					.build();

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Naksh Phone");
			inventory1.setQuantity(1);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
