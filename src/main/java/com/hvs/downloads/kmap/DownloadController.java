package com.hvs.downloads.kmap;

import com.sun.javafx.scene.shape.PathUtils;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import sun.misc.ClassLoaderUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public ResponseEntity<InputStreamResource> downloadKMap() throws URISyntaxException, IOException {
        URL url = new ClassPathResource("/downloads/" + APP).getURL();
        File file = new File(url.toURI());
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + APP
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                .body(inputStreamResource);
    }

}
