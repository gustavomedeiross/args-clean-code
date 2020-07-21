public class ArgumentMarshalerFactory {
    public ArgumentMarshaler make(String signature) throws InvalidMarshalerTypeException {
        if (signature.length() == 0)
            return new BooleanArgumentMarshaler();
        else if (signature.equals("*"))
            return new StringArgumentMarshaler();
        else if (signature.equals("#"))
            return new IntegerArgumentMarshaler();
        else if (signature.equals("##"))
            return new DoubleArgumentMarshaler();
        else
            throw new InvalidMarshalerTypeException();
    }

    public class InvalidMarshalerTypeException extends Exception {}
}
