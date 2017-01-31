package sanchez.Admin;

import sanchez.Util.BadCommandException;
import sanchez.Util.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class shutdown implements Command
{

    @Override
    public void run(MessageReceivedEvent e, String[] args) throws BadCommandException
    {
        e.getMessage().deleteMessage().queue();
        System.exit(0);
    }

    @Override
    public int getPermLevel()
    {
        return 2;
    }
}
