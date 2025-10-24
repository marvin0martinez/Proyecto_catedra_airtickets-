/* global React, ReactDOM */
const { createRoot } = ReactDOM;

function App() {
  return (
    <div className="min-h-screen bg-[#0b1736] text-white">
      <header className="px-6 py-4 border-b border-white/10">
        <h1 className="text-2xl font-bold">AirTickets</h1>
        <p className="text-white/70 text-sm mt-1">Mockups Figma — Vista Home</p>
      </header>

      <main className="p-6">
        <div className="rounded-xl border border-white/10 p-6">
          <h2 className="text-xl font-semibold mb-3">¡Listo! React está renderizando</h2>
          <p className="text-white/80">
            Si ves esto, el problema era la carga/ejecución de <code>main.jsx</code>.
          </p>
          <p className="text-white/60 mt-2">
            Ahora podemos integrar tus componentes de páginas y el sidebar.
          </p>
        </div>
      </main>
    </div>
  );
}

document.addEventListener("DOMContentLoaded", () => {
  const el = document.getElementById("root");
  if (!el) {
    console.error("No existe #root en el HTML");
    return;
  }
  createRoot(el).render(<App />);
});
