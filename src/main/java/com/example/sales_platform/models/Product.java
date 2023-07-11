package com.example.sales_platform.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private String city;
    @OneToMany(cascade = ALL, fetch = LAZY,
    mappedBy = "product")
    private List<Image> images = new ArrayList<>();
    @ManyToOne(cascade = REFRESH, fetch = LAZY)
    @JoinColumn
    private User user;
    private Long previewImageId;
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void onCreate() { dateOfCreated = LocalDateTime.now(); }


    public void addImageToProduct(Image image) {
        image.setProduct(this);
        images.add(image);
    }
}
