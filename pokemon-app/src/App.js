import logo from "./pokeball.png";
import PokemonView from "./PokemonView";
import "./App.css";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="Pokemon-logo" alt="logo" />
      </header>
      <div className="App-body">
        <PokemonView />
      </div>
    </div>
  );
}

export default App;
