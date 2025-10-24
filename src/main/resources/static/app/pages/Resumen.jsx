
function Resumen({res, pax}){
  return (
    <div className="container grid grid-2">
      <div className="glass card">
        <div className="title">Resumen de Vuelo</div>
        <div className="small" style={{marginTop:8}}>
          {res? <>Vuelo: {res.flight?.airline?.name} • {res.flight?.origin?.iata}→{res.flight?.destination?.iata} • {new Date(res.flight?.departureTime).toLocaleString()}</> : '—'}
        </div>
      </div>
      <div className="glass card">
        <div className="title">Datos de Pasajero</div>
        <div className="small" style={{marginTop:8}}>
          {pax? <>{pax.firstName} {pax.lastName} • {pax.documentId}</> : '—'}
        </div>
      </div>
      <div className="glass card"><b>Total a Pagar:</b> ${res?.amount ?? '-'}</div>
    </div>
  );
}

export default Resumen;
