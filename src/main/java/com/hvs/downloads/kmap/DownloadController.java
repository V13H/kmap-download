package com.hvs.downloads.kmap;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InputStream;

@Controller
@RequestMapping("/download")
public class DownloadController {
    private static final String APP = "app-debug.apk";
    private static final String TEST = "text.txt";

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/k-map")
    public ResponseEntity<InputStreamResource> downloadKMap() {
        InputStream resourceAsStream = getClass().getResourceAsStream("/downloads/app-debug.apk");
        InputStreamResource in = new InputStreamResource(resourceAsStream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + APP
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(in);
    }

}
