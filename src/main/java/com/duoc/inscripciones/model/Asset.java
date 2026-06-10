package com.duoc.inscripciones.model;

import lombok.Builder;
import lombok.Value;

import java.net.URL;

@Value
@Builder
public class Asset {
    private String key;
    private String lastModified;
    private Long size;
    private URL url;

}
