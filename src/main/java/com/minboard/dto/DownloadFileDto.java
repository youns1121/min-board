package com.minboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class DownloadFileDto {
    private String resource;
    private String contentDisposition;

    @Builder
    public DownloadFileDto(String resource, String contentDisposition) {
        this.resource = resource;
        this.contentDisposition = contentDisposition;
    }
}
