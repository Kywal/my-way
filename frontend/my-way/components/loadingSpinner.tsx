import React from "react";
import { Spinner } from "@heroui/react";

export const LoadingSpinner: React.FC = () => {
  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-[5000]">
      <Spinner size="lg" />
    </div>
  );
};
