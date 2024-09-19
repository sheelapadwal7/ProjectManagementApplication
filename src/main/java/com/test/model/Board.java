package com.test.model;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="board")
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="boardName")
	private String boardName;
	
	 @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	 private Set<Lists> lists;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public Set<Lists> getLists() {
		return lists;
	}

	public void setLists(Set<Lists> lists) {
		this.lists = lists;
	}


	


}
