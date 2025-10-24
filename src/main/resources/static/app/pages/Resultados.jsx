
function Resultados({flights=[], onPick}){
  return (
    <div className="container grid gap-12">
      {(flights.length===0) && <div className="hint">No hay resultados aún.</div>}
      {flights.map(f=>(
        <div key={f.id} className="ticket glass">
          <div className="top">
            <b>{f.origin?.iata}</b><span>→</span><b>{f.destination?.iata}</b>
            <span className="small">• {new Date(f.departureTime).toLocaleString()}</span>
            <span className="price">${f.baseFare}</span>
          </div>
          <div className="small" style={{marginTop:6}}>{f.airline?.name} • Llega {new Date(f.arrivalTime).toLocaleString()} • {f.aircraftType}</div>
          <div className="row" style={{marginTop:10}}>
            <button className="primary" onClick={()=>onPick(f)}>Seleccionar</button>
          </div>
        </div>
      ))}
    </div>
  );
}

export default Resultados;
