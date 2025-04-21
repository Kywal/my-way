'use client'

import { SessionProvider } from "next-auth/react";

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <section>
      <div> 
        <SessionProvider>{children}</SessionProvider>
      </div>
    </section>
  );
}
