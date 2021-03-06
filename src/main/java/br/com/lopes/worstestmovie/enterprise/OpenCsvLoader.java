package br.com.lopes.worstestmovie.enterprise;

import br.com.lopes.worstestmovie.model.movie.Movie;
import br.com.lopes.worstestmovie.model.movie.MovieRepository;
import br.com.lopes.worstestmovie.model.producer.ProducerService;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenCsvLoader {

    private static final Logger LOG = LoggerFactory.getLogger(OpenCsvLoader.class);

    protected static final String[] HEADERS = new String[]{"year", "title", "studios", "producers", "winner"};

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerService producerService;

    @Value("${datasource.csv.file}")
    private String csvFile;

    public void loadDataIntoH2(){
        List<Movie> collect = getCsvMovies()
                .stream()
                .map(csvMovieWrapper -> csvMovieWrapper.toEntity(producerService))
                .collect(Collectors.toList());
        movieRepository.saveAll(collect);
    }

    private List<CsvMovieWrapper> getCsvMovies() {
        try(FileReader fileReader = new FileReader(csvFile)){
            return parseCsvFile(fileReader);
        } catch (FileNotFoundException e) {
            LOG.error("CSV file not found",  e);
        } catch (IOException e) {
            LOG.error("CSV file read exception",  e);
        }
        return Collections.emptyList();
    }

    private List<CsvMovieWrapper> parseCsvFile(/*InputStream is*/FileReader fileReader) {
        List<CsvMovieWrapper> movies = new ArrayList<>();
        try {
            movies = parseEntities(fileReader);
        } catch (Exception e) {
            LOG.error("CSV casting error",  e);
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                LOG.error("Closing fileReader/csvParser Error!",  e);
            }
        }

        return movies;
    }

    private List<CsvMovieWrapper> parseEntities(FileReader fileReader) {
        ColumnPositionMappingStrategy<CsvMovieWrapper> mappingStrategy = new ColumnPositionMappingStrategy<>();
        mappingStrategy.setType(CsvMovieWrapper.class);
        mappingStrategy.setColumnMapping(HEADERS);
        CsvToBean<CsvMovieWrapper> csvToBean = new CsvToBeanBuilder<CsvMovieWrapper>(fileReader)
                .withMappingStrategy(mappingStrategy)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(';').build();
        return csvToBean.parse();
    }

}
