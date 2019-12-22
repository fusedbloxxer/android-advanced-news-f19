package ro.atelieruldigital.news.model.ws.response;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Source;

public class SourcesResponse extends Response {
    private List<Source> sources;

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    @NotNull
    @Override
    public String toString() {
        return "SourcesResponse{" + super.toString() +
                ", sources=" + sources +
                '}';
    }
}
