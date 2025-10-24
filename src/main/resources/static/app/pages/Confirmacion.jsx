
function Confirmacion({locator}){
  return (
    <div className="container">
      <div className="glass card">
        <div className="title" style={{color:'#7be495'}}>¡Reserva Confirmada!</div>
        <div className="small">Tu compra fue procesada exitosamente. Código de reserva/PNR: <b>{locator||'-'}</b></div>
        <div className="row" style={{marginTop:12}}>
          <a className="primary" href="#/vuelos/buscar"><button className="primary">Hacer nueva búsqueda</button></a>
        </div>
      </div>
    </div>
  );
}

export default Confirmacion;
