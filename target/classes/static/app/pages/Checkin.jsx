import { useState, useEffect } from 'react';

function Checkin() {
  const [selectedSeat, setSelectedSeat] = useState(null);
  const [seats, setSeats] = useState([]);
  const [searchSeat, setSearchSeat] = useState('');

  // Configuración inicial de los asientos
  useEffect(() => {
    const initializeSeats = () => {
      const rows = ['A', 'B', 'C'];
      const seatMap = [];
      
      for (let row = 0; row < rows.length; row++) {
        for (let seatNum = 1; seatNum <= 12; seatNum++) {
          const seatId = `${rows[row]}${seatNum}`;
          const isOccupied = Math.random() > 0.7;
          const isMySeat = seatId === 'B6';
          
          seatMap.push({
            id: seatId,
            row: rows[row],
            number: seatNum,
            occupied: isOccupied,
            isMySeat: isMySeat,
          });
        }
      }
      setSeats(seatMap);
      
      const mySeat = seatMap.find(seat => seat.isMySeat);
      if (mySeat) {
        setSelectedSeat(mySeat.id);
      }
    };

    initializeSeats();
  }, []);

  const handleSeatClick = (seatId) => {
    const seat = seats.find(s => s.id === seatId);
    if (!seat.occupied) {
      setSelectedSeat(seatId);
    }
  };

  const handleSearchSeat = () => {
    if (searchSeat.trim()) {
      const formattedSeat = searchSeat.toUpperCase().replace(/\s/g, '');
      const seatExists = seats.find(seat => seat.id === formattedSeat);
      
      if (seatExists) {
        if (!seatExists.occupied) {
          setSelectedSeat(formattedSeat);
        } else {
          alert('Este asiento ya está ocupado');
        }
      } else {
        alert('Asiento no encontrado');
      }
    }
  };

  const handleSaveChanges = () => {
    if (selectedSeat) {
      console.log('Asiento seleccionado:', selectedSeat);
      alert(`Asiento ${selectedSeat} guardado exitosamente`);
    } else {
      alert('Por favor selecciona un asiento');
    }
  };

  const getSeatClasses = (seat) => {
    const baseClasses = "w-8 h-8 rounded flex items-center justify-center text-xs font-bold cursor-pointer transition-all border-2";
    
    if (seat.isMySeat) {
      return `${baseClasses} bg-yellow-100 text-yellow-800 border-yellow-400`;
    }
    if (seat.occupied) {
      return `${baseClasses} bg-neutral-200 text-neutral-500 border-neutral-300 cursor-not-allowed`;
    }
    if (seat.id === selectedSeat) {
      return `${baseClasses} bg-green-500 text-white border-green-600 scale-110`;
    }
    return `${baseClasses} bg-blue-100 text-blue-800 border-blue-300 hover:bg-blue-200`;
  };

  const getSeatLabel = (seat) => {
    if (seat.isMySeat) return '⭐';
    if (seat.occupied) return '✖';
    return seat.number;
  };

  return (
    <div className="p-6">
      <div className="card bg-white rounded-xl p-6 shadow-md">
        <div className="text-2xl font-bold text-neutral-800 mb-2">
          Check-in / Selección de asientos
        </div>
        <div className="text-sm text-neutral-500 mb-6">
          Selecciona o busca tu asiento preferido
        </div>
        
        <div className="flex flex-col lg:flex-row gap-7 items-start">
          {/* Panel de controles - Izquierda */}
          <div className="w-full lg:w-80 flex flex-col gap-6">
            <h3 className="text-lg font-semibold text-neutral-700">
              Check-in Selección de vuelo
            </h3>
            
            {/* Leyenda de asientos */}
            <div className="flex flex-col gap-3">
              <div className="flex items-center gap-3">
                <div className="w-6 h-6 bg-blue-100 border-2 border-blue-300 rounded flex items-center justify-center text-xs">1</div>
                <span className="text-sm text-neutral-600">Disponible</span>
              </div>
              <div className="flex items-center gap-3">
                <div className="w-6 h-6 bg-neutral-200 border-2 border-neutral-300 rounded flex items-center justify-center text-xs">✖</div>
                <span className="text-sm text-neutral-600">Ocupado</span>
              </div>
              <div className="flex items-center gap-3">
                <div className="w-6 h-6 bg-yellow-100 border-2 border-yellow-400 rounded flex items-center justify-center text-xs">⭐</div>
                <span className="text-sm text-neutral-600">Tu asiento actual</span>
              </div>
              <div className="flex items-center gap-3">
                <div className="w-6 h-6 bg-green-500 border-2 border-green-600 rounded flex items-center justify-center text-xs text-white">5</div>
                <span className="text-sm text-neutral-600">Seleccionado</span>
              </div>
            </div>

            {/* Búsqueda de asiento */}
            <div className="space-y-2">
              <label className="label text-neutral-700 font-medium">
                Buscar Asiento
              </label>
              <div className="flex gap-2">
                <input 
                  className="input flex-1 border-neutral-300 focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                  placeholder="Ej: A10, B5, C12"
                  value={searchSeat}
                  onChange={(e) => setSearchSeat(e.target.value)}
                  onKeyPress={(e) => e.key === 'Enter' && handleSearchSeat()}
                />
                <button 
                  className="btn bg-neutral-200 hover:bg-neutral-300 text-neutral-700 font-medium px-4"
                  onClick={handleSearchSeat}
                >
                  Buscar
                </button>
              </div>
            </div>

            {/* Información del asiento seleccionado */}
            {selectedSeat && (
              <div className="p-4 bg-green-50 rounded-lg border-l-4 border-green-500">
                <div className="text-sm text-green-800">
                  <strong>Asiento seleccionado:</strong> 
                  <span className="ml-2 font-bold text-lg">{selectedSeat}</span>
                </div>
              </div>
            )}

            {/* Botón de guardar */}
            <button 
              className="btn-primary bg-black hover:bg-neutral-800 text-white font-medium py-3"
              onClick={handleSaveChanges}
            >
              Guardar Cambios
            </button>

            {/* Información adicional */}
            <div className="text-neutral-500 text-xs leading-relaxed border-t pt-4">
              <p className="font-medium mb-1">Instrucciones:</p>
              <p>A, B, C = Filas de asientos</p>
              <p>Los números indican la posición en la fila</p>
              <p className="mt-2">Recomendación: seguir indicaciones de cabina y crew.</p>
            </div>
          </div>

          {/* Plano de asientos - Derecha */}
          <div className="flex-1 flex justify-center items-center w-full">
            <div className="bg-white rounded-2xl p-6 shadow-lg border border-neutral-200 w-full max-w-md">
              {/* Cabecera del avión */}
              <div className="text-center font-bold p-4 bg-neutral-100 rounded-t-xl mb-6 text-neutral-700 border-b">
                ✈️ CABINA PRINCIPAL
              </div>
              
              {/* Asientos organizados por filas */}
              <div className="flex flex-col gap-4 mb-6">
                {['A', 'B', 'C'].map(row => (
                  <div key={row} className="flex items-center justify-between gap-3">
                    <div className="font-bold text-neutral-600 w-6 text-center text-sm">{row}</div>
                    <div className="flex gap-2 flex-wrap justify-center flex-1">
                      {seats
                        .filter(seat => seat.row === row)
                        .map(seat => (
                          <div
                            key={seat.id}
                            className={getSeatClasses(seat)}
                            onClick={() => handleSeatClick(seat.id)}
                            title={`Asiento ${seat.id} - ${
                              seat.occupied ? 'Ocupado' : 
                              seat.isMySeat ? 'Tu asiento actual' : 
                              'Disponible'
                            }`}
                          >
                            {getSeatLabel(seat)}
                          </div>
                        ))
                      }
                    </div>
                    <div className="font-bold text-neutral-600 w-6 text-center text-sm">{row}</div>
                  </div>
                ))}
              </div>

              {/* Salida de emergencia */}
              <div className="text-center font-bold p-3 bg-red-100 rounded-b-xl text-red-800 border-t">
                🚪 SALIDA DE EMERGENCIA
              </div>

              {/* Indicadores de filas */}
              <div className="flex justify-between mt-4 text-xs text-neutral-500">
                <span>Frente del avión →</span>
                <span>← Parte trasera</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Checkin;