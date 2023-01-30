import { useEffect, useState } from "react";
import SinglePokemonInfo from "./SinglePokemonInfo";

import { compareNames } from "./utils";

function PokemonView() {
  const [allPokemons, setAllPokemons] = useState([]);
  const [selectedPokemon, setSelectedPokemon] = useState(undefined);

  useEffect(() => {
    fetch("http://localhost:8080", {
      headers: { "Access-Control-Allow-Origin": "*" },
    })
      .then((response) => response.json())
      .then((data) => setAllPokemons(data));
  }, []);

  const getSinglePokemonInfo = (event) => {
    const id = event.target.value;
    const url = `http://localhost:8080/${id}`;
    fetch(url, {
      headers: { "Access-Control-Allow-Origin": "*" },
    })
      .then((response) => response.json())
      .then((data) => setSelectedPokemon(data));
  };

  const getAllPokemonDropdown = () => {
    return (
      <select onChange={(event) => getSinglePokemonInfo(event)}>
        <option value="default">--Select Pokemon--</option>
        {allPokemons.sort(compareNames).map((pokemon) => (
          <option value={pokemon.id} key={pokemon.id}>
            {pokemon.name}
          </option>
        ))}
      </select>
    );
  };

  return (
    <div>
      {getAllPokemonDropdown()}
      {selectedPokemon !== undefined && (
        <SinglePokemonInfo {...selectedPokemon} />
      )}
    </div>
  );
}

export default PokemonView;
