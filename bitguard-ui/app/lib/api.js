export async function transmitMessage(message, errorProbability) {
  const res = await fetch("http://localhost:8080/api/transmit", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      message,
      errorProbability
    })
  });

  return res.json();
}