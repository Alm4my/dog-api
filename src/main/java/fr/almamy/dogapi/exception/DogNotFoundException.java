package fr.almamy.dogapi.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Dog Not Found")
@NoArgsConstructor
public class DogNotFoundException extends RuntimeException implements GraphQLError {
    //\\ Fields \\//
    private final Map<String, Object> extensions = new HashMap<>();

    //\\ Constructors \\//
    public DogNotFoundException(String message, Long invalidDogId){
        super(message);
        extensions.put("invalidbreed", invalidDogId);
    }

    //\\ Public Methods \\//
    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
