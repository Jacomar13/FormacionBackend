package javier.correa.block13mongodb.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "javier.correa.repository")
public class MongoDBConfig {
    public String getDatabaseName() {
        return "myMongoDB";
    }
    @Bean
    public MongoClient mongoClient() {
        ServerAddress address = new ServerAddress("127.0.0.1", 27017);
        MongoCredential credential = MongoCredential.createCredential("mdbUser", getDatabaseName(), "cp".toCharArray());
        MongoClientOptions options = new MongoClientOptions.Builder().build();

        MongoClient client = new MongoClient(address, credential, options);
        return client;
    }
    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory((com.mongodb.client.MongoClient) mongoClient(), getDatabaseName());
        return factory;
    }
    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate template = new MongoTemplate(mongoDbFactory());
        return template;
    }
}
