package me.kolek.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.type.Argument;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

@MicronautTest
public class ExampleControllerTest {
  @Inject
  @Client("/example")
  HttpClient client;

  @Test
  public void testExample0() {
    Argument<Map> type = Argument.of(Map.class, String.class, String.class);
    Map<String, String> result = client.toBlocking()
        .retrieve(HttpRequest.GET("/0"), type);
    assertEquals(result, Map.of(
        "val1", "abc",
        "val2", "123",
        "val3", "true"
    ));
  }

  @Test
  public void testExample1() {
    Argument<List> type = Argument.of(List.class, Argument.of(Map.class, String.class, String.class));
    List<Map<String, String>> result = client.toBlocking()
        .retrieve(HttpRequest.GET("/1"), type);
    assertEquals(result, List.of(Map.of(
        "val1", "abc",
        "val2", "123",
        "val3", "true"
    )));
  }

  @Test
  public void testExample2() {
    Argument<Map> type = Argument.of(Map.class,
        Argument.of(String.class),
        Argument.of(List.class, Argument.of(Map.class, String.class, String.class)));
    Map<String, List<Map<String, String>>> result = client.toBlocking()
        .retrieve(HttpRequest.GET("/2"), type);
    assertEquals(result, Map.of(
        "val1", "abc",
        "val2", "123",
        "val3", "true"
    ));
  }
}
