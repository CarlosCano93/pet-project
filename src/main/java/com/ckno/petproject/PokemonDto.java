package com.ckno.petproject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
record PokemonDto(List<Type> types) {}

record Type(int slot, TypeName type) {}

record TypeName(String name) {}
