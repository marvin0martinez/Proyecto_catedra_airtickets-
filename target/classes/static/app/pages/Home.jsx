
function Home(){
  return (
    <div className="container grid gap-12">
      <div className="title">Mejores vuelos con Airtix</div>
      <div className="grid grid-4">
        <div className="glass card" style={{height:140}}/>
        <div className="glass card" style={{height:140}}/>
        <div className="glass card" style={{height:140}}/>
        <div className="glass card" style={{height:140}}/>
      </div>
      <a className="hint" href="#/vuelos/buscar">→ Vuelos</a>
    </div>
  );
}

export default Home;
