package fr.almamy.dogapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Joke {
    //\\ Fields \\//
    private Long id;
    private String type;
    private String setup;
    private String punchline;
}
