package com.ckno.petproject.urlshortener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/shortener")
@RequiredArgsConstructor
class UrlShortenerController {

    private final HashGenerator hashGenerator;
    private final Map<String, String> hashUrl = new HashMap<>();

    @PostMapping
    public HashResponse createShortUrl(@PathParam("url") String url) {
        String hash = hashGenerator.newHash();
        hashUrl.put(hash, url);

        return new HashResponse(hash);
    }

    @GetMapping
    public RedirectView getUrl(@PathParam("hash") String hash) {
        return new RedirectView(hashUrl.get(hash));
    }

    record HashResponse(String hash) {}
}

