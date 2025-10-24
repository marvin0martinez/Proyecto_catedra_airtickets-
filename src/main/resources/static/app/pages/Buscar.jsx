
function Buscar({onSearch}){
  const [origin,setOrigin]=React.useState('');
  const [dest,setDest]=React.useState('');
  const [date,setDate]=React.useState('');
  const [pax,setPax]=React.useState(1);
  return (
    <div className="container">
      <div className="glass card">
        <div className="title">Buscar vuelo</div>
        <div className="grid grid-4" style={{marginTop:12}}>
          <div><div className="small">Ciudad de origen</div><input value={origin} onChange={e=>setOrigin(e.target.value)} placeholder="SAL"/></div>
          <div><div className="small">Ciudad de destino</div><input value={dest} onChange={e=>setDest(e.target.value)} placeholder="MIA"/></div>
          <div><div className="small">Fecha de salida</div><input type="date" value={date} onChange={e=>setDate(e.target.value)}/></div>
          <div><div className="small">N° Pasajeros</div><input type="number" min="1" value={pax} onChange={e=>setPax(e.target.value)}/></div>
        </div>
        <div className="row" style={{marginTop:12, justifyContent:'center'}}>
          <button className="primary" onClick={()=>onSearch({origin,dest,date,pax})}>Buscar vuelos</button>
        </div>
      </div>
    </div>
  );
}

export default Buscar;
