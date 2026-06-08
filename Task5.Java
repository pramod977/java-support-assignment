private static final Logger logger =
        LoggerFactory.getLogger(DocumentValidator.class);

public ValidationResult validate(Document doc) {

    try {

        if (doc == null) {
            // Expected validation failure
            throw new IllegalArgumentException("Document is null");
        }

        String content = doc.extractContent();

        if (content.isEmpty()) {
            // Expected validation failure
            throw new IllegalArgumentException("Empty content");
        }

        return runValidationRules(content);

    } catch (IllegalArgumentException e) {

        // Issue 1 Fix:
        // Replace printStackTrace() with SLF4J warn logging
        logger.warn("Validation failed: {}", e.getMessage());

        // Issue 2 Fix:
        // Return a proper ValidationResult instead of null
        return ValidationResult.invalid(e.getMessage());

    } catch (Exception e) {

        logger.error("Unexpected validation error", e);
        throw e;
    }
}

public void validateBatch(List<Document> docs) {

    for (Document doc : docs) {

        try {

            ValidationResult r = validate(doc);

            // Issue 3 Fix:
            // Prevent NullPointerException and save only valid results
            if (r != null && r.isValid()) {
                saveResult(r);
            }

        } catch (Exception e) {

            // Issue 4 Fix:
            // Do not silently swallow exceptions
            logger.error("Failed to process document in batch", e);
        }
    }
}
