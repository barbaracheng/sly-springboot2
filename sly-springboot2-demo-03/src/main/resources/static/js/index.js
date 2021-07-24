var node = document.getElementById("linknav");
node.onclick = function (ev) {
    node.style.color = "#20a0ff";
    // node.className = 'active';
    console.log("你点击了"+ev.currentTarget);
};