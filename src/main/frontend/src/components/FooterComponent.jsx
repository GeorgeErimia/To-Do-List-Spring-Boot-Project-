import React from "react";

const FooterComponent = () => {
  return (
    <div>
      <footer
        className="footer"
        style={{
          position: "absolute",
          bottom: "0",
          width: "100%",
          maxWidth: "1280px",
          height: "60px",
          backgroundColor: "black",
          color: "white",
          textAlign: "center",
        }}
      >
        <p className="text-center">Todo App by George Erimia</p>
      </footer>
    </div>
  );
};

export default FooterComponent;
