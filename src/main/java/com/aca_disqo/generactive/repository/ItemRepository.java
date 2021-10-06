package com.aca_disqo.generactive.repository;

import com.aca_disqo.generactive.repository.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
