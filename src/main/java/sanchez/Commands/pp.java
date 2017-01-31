package sanchez.Commands;

import sanchez.Util.BadCommandException;
import sanchez.Util.Command;
import sanchez.Util.FileDownloader;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.*;
import java.net.URL;

public class pp implements Command {
    @Override
    public void run(MessageReceivedEvent e, String[] args) throws BadCommandException {
        if (args.length < 2) {
            throw new BadCommandException("Usage: !pp [map url] [acc]% +[mods] [combo]x [misses]m");
        }
        if (args[1].contains("/s/"))
            throw new BadCommandException("Make sure you're using the correct link to the beatmap; it should include '/b/' in the url.");

        String input = args[1].replace("/b/", "/osu/");

        File file = null;
        Process oppai;

        try {
            String result = FileDownloader.downloadFromUrl(new URL(input), "map.osu");

            file = new File(result);

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (file != null) {
            try {

                oppai = new ProcessBuilder("lib\\oppai.exe", file.getAbsolutePath()).start();

                if (args.length > 2)
                    if (!args[2].contains("%") && !args[2].contains("m") && !args[2].contains("x") && !args[2].contains("+"))
                        args[2] = args[2] + "%";

                if (args.length == 3) {
                    oppai = new ProcessBuilder("lib\\oppai.exe", file.getAbsolutePath(), args[2]).start();
                }

                if (args.length == 4)
                    oppai = new ProcessBuilder("lib\\oppai.exe", file.getAbsolutePath(), args[2], args[3]).start();

                if (args.length == 5)
                    oppai = new ProcessBuilder("lib\\oppai.exe", file.getAbsolutePath(), args[2], args[3], args[4]).start();

                InputStream is = oppai.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String read = null;
                String line;

                while ((line = br.readLine()) != null) {
                    read = read + line + "\n";
                }

                System.out.println(read);

                if (read != null) {
                    e.getChannel().sendMessage("```" + read.substring(162) + "```").queue();
                }else {
                    throw new BadCommandException("Internal failure; perhaps oppai couldn't understand your input?");
                }

                oppai.destroy();
                is.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            file.delete();
        }
    }

    @Override
    public int getPermLevel() {
        return 0;
    }
}
