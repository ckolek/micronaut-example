package me.kolek.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.List;
import java.util.Map;

@Controller("/example")
public class ExampleController {
  @Get("/0")
  public Map<String, String> test0() {
    return Map.of(
        "val1", "abc",
        "val2", "123",
        "val3", "true"
    );
  }

  @Get("/1")
  public List<Map<String, String>> test1() {
    return List.of(Map.of(
        "val1", "abc",
        "val2", "123",
        "val3", "true"
    ));
  }

  @Get("/2")
  public Map<String, List<Map<String, String>>> test2() {
    return Map.of("keys", List.of(Map.of(
        "val1", "abc",
        "val2", "123",
        "val3", "true"
    )));
  }
}
