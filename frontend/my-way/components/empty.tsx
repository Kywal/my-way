import React from "react";
import { BoxIcon } from "./icons";

type EmptyProps = {
  children?: React.ReactNode;
};

export const Empty = ({ children }: EmptyProps) => {
  return (
    <div className="flex flex-col items-center justify-center">
      <BoxIcon className="text-white fill-white w-28 h-28" />
      <div className="text-white">{children}</div>
    </div>
  );
};
