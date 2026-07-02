async function changeQuantity(articoloId, delta) {
    const params = new URLSearchParams();
    params.set("articoloId", articoloId);
    params.set("quantita", delta);
    const res = await fetch(`${contextPath}/common/AddToCartServlet`, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params.toString()
    });

    if (res.ok) {
        location.reload();
    }
}
