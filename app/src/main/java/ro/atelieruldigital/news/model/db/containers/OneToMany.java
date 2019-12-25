package ro.atelieruldigital.news.model.db.containers;

import java.util.List;

public class OneToMany<One extends String, Many> {
    private One one;

    private List<Many> many;

    public OneToMany(One one, List<Many> many) {
        this.one = one;
        this.many = many;
    }

    public One getOne() {
        return one;
    }

    public void setOne(One one) {
        this.one = one;
    }

    public List<Many> getMany() {
        return many;
    }

    public void setMany(List<Many> many) {
        this.many = many;
    }
}
