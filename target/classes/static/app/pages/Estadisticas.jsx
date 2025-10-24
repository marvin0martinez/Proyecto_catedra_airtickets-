
function Estadisticas({summary}){
  return (
    <div className="container grid grid-3">
      <div className="glass card"><div className="small">Total Reservas</div><div className="title">{summary?.totalReservations ?? '-'}</div></div>
      <div className="glass card"><div className="small">Ingresos Totales</div><div className="title">${summary?.totalRevenue ?? '-'}</div></div>
      <div className="glass card"><div className="small">Total Pasajeros</div><div className="title">{summary?.totalPassengers ?? '-'}</div></div>
    </div>
  );
}

export default Estadisticas;
