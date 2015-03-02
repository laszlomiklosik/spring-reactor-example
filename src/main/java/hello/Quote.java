package hello;

public class Quote {

    private Long id;
    private String quote;

    public Quote(Long id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    public Long getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }

    @Override
    public String toString() {
        return "Quote {" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
