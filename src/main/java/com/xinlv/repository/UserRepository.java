package com.xinlv.repository;

import org.springframework.data.graph.neo4j.repository.GraphRepository;

import com.xinlv.domain.User;

public interface UserRepository  extends GraphRepository<User>{

}
