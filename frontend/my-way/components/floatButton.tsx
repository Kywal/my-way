import { Button } from "@heroui/button";
import React from "react";

interface FloatButtonProps {
  onClick?: () => void;
}

export const FloatButton = ({ onClick }: FloatButtonProps) => {
  return (
    <Button
      className="fixed bottom-4 right-4 p-3 rounded-full shadow-lg h-[60px] w-[60px] min-w-4 min-h-4 text-2xl"
      onPress={onClick}
      color="primary"
    >
      +
    </Button>
  );
};
