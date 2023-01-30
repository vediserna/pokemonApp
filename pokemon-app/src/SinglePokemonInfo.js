import styled from "styled-components";

const StyledPokemonTable = styled.table`
  color: white;
  min-width: 200px;
`;

const StyledTableData = styled.td`
  border: 1px solid white;
`;

function SinglePokemonInfo(props) {
  return (
    <div>
      <h2>{props.name}</h2>
      <img src={props.imgPath} />
      <StyledPokemonTable>
        <tbody>
          <tr>
            <StyledTableData>height:</StyledTableData>
            <StyledTableData>{props.height}</StyledTableData>
          </tr>
          <tr>
            <StyledTableData>weight:</StyledTableData>
            <StyledTableData>{props.weight}</StyledTableData>
          </tr>
          <tr>
            <StyledTableData>types:</StyledTableData>
            {props.types.map((type) => (
              <StyledTableData key={type}>{type}</StyledTableData>
            ))}
          </tr>
          <tr>
            <StyledTableData>height:</StyledTableData>
            <StyledTableData>{props.height}</StyledTableData>
          </tr>
        </tbody>
      </StyledPokemonTable>
    </div>
  );
}

export default SinglePokemonInfo;
