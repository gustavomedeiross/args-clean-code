import java.util.HashMap;
import java.util.Map;

public class Schema {
    private Map<Character, ArgumentMarshaler> marshalers = new HashMap<Character, ArgumentMarshaler>();

    public Schema(String template) throws ArgsException {
        parse(template);
    }

    private void parse(String template) throws ArgsException {
        for (String element : template.split(",")) {
            if(element.length() > 0) {
                SchemaElement s = new SchemaElement(element.trim());
                marshalers.put(s.getId(), s.getMarshaler());
            }
        }
    }

    public Map<Character, ArgumentMarshaler> getMarshalers() {
        return marshalers;
    }

    private class SchemaElement {
        private char id;
        private String tail;

        SchemaElement(String element) throws ArgsException {
            id = element.charAt(0);
            tail = element.substring(1);
            validateId();
        }

        private void validateId() throws ArgsException {
            if (!Character.isLetter(id))
                throw new ArgsException(ArgsException.ErrorCode.INVALID_ARGUMENT_NAME, id, null);
        }

        ArgumentMarshaler getMarshaler() throws ArgsException {
            try {
                return new ArgumentMarshalerFactory().make(tail);
            } catch (ArgumentMarshalerFactory.InvalidMarshalerTypeException e) {
                throw new ArgsException(ArgsException.ErrorCode.INVALID_FORMAT, id, tail);
            }
        }

        char getId() {
            return id;
        }
    }
}
