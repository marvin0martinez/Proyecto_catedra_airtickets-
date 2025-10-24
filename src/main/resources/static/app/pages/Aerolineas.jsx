
function Aerolineas(){
  const logos = ['Delta Air','Avianca','LATAM','Iberia','American Airlines','Qatar Air','Emirates Airways','Volaris'];
  return (
    <div className="container">
      <div className="title">Aerolíneas</div>
      <div className="grid grid-4" style={{marginTop:12}}>
        {logos.map((n,i)=>(<div key={i} className="glass card" style={{height:120, display:'flex', alignItems:'center', justifyContent:'center'}}>{n}</div>))}
      </div>
    </div>
  );
}

export default Aerolineas;
