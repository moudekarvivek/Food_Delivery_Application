package com.substring.foodie.controller;

import com.substring.foodie.dto.FileData;
import com.substring.foodie.dto.RestaurantDto;
import com.substring.foodie.service.FileService;
import com.substring.foodie.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    @Value("${restaurant.file.path}")
    private String bannerFolderPath;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestaurantService restaurantService;

    //Constructor Injection
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //Creating Restaurant
    @PostMapping
    public ResponseEntity<RestaurantDto> create(
            @RequestBody RestaurantDto restaurantDto
    ){
        RestaurantDto restaurantDtoResult = restaurantService.saveRestaurant(restaurantDto);
        return ResponseEntity.ok(restaurantDtoResult);
    }

    //Update Restaurant
    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> update(
            @RequestBody RestaurantDto restaurantDto,
            @PathVariable String restaurantId){

        RestaurantDto restaurantDto1 = restaurantService.updateRestaurant(restaurantDto, restaurantId);
        return ResponseEntity.ok(restaurantDto1);
    }

    //Get All Restaurant
    @GetMapping
    public ResponseEntity<Page<RestaurantDto>> findAll(
            @RequestParam(value = "page",required = false,defaultValue = "0") int page,
            @RequestParam(value = "size",required = false,defaultValue = "10") int size,
            @RequestParam(value = "sortBy",required = false,defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir",required = false,defaultValue = "desc") String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(restaurantService.getAll(pageable));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> findById(@PathVariable("restaurantId") String id){
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    //api to handle restaurant Banner
    @PostMapping("/upload-banner/{restaurantId}")
    public ResponseEntity<?> uploadFile(
            @RequestParam("banner") MultipartFile banner,
            @PathVariable String restaurantId) throws IOException {

        logger.info("Upload the banner file");
        logger.info(banner.getOriginalFilename());
        logger.info(banner.getContentType());

        //  Image + restaurantId

        //  Call some file service:
        RestaurantDto restaurantDto = restaurantService.uploadBanner(banner, restaurantId);
        return ResponseEntity.ok(restaurantDto);
    }

    //api to serve banner:
    @GetMapping("{restaurantId}/banner")
    public ResponseEntity<Resource> serveFile(@PathVariable String restaurantId) throws IOException
    {
        RestaurantDto restaurantDto = restaurantService.getRestaurantById(restaurantId);

        String fullPath=bannerFolderPath+restaurantDto.getBanner();

        Path filePath = Paths.get(fullPath);

        Resource resource = new UrlResource(filePath.toUri());

        if(resource.exists()){

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);

        }else{
            throw new FileNotFoundException("File Not Found: "+fullPath);
        }
    }
}
