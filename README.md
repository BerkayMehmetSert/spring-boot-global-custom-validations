# Global Custom Validations

This is a custom validation for the API request. It is used to validate the request parameters and throw an exception if the validation fails.

### Usage

```java
// EntityValidation.java
public class EntityValidation implements ValidationService {
    // ...
}
```

```java
public void foo(String value) {
    ValidationRules.run(() -> {
        entityValidation.notEmpty(value, "message");
        entityValidation.maxLength(value, 10, "message");
        entityValidation.minLength(value, 5, "message");
        entityValidation.greaterThan(value, 0, "message");
        entityValidation.lessThan(value, 100, "message");
        entityValidation.greaterThanOrEqual(value, 0 "message");
        entityValidation.lessThanOrEqual(value, 100, "message");
        entityValidation.email(value, "regex", "message");
        entityValidation.password(value, "regex", "message");
        });
    // ...
}
```

### Error Result

```json
{
  "type": "https://example.com/validation-exception",
  "title": "Validation Exception",
  "status": 400,
  "detail": "Exception message",
  "instance": "/api/controller",
  "timestamp": "2023-02-23 17:17:15"
}
```